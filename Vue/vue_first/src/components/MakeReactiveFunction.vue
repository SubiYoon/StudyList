<template>
  <input v-model="product.p" @input="printll"/>
</template>

<script>
export default {
    name: "ReactiveSampleTest",
    setup() {
        // 객체를 key로 사용하기 위한 WeakMap
        const targetMap = new WeakMap;
        // Method를 동적으로 사용하기 위한 acctiveMethod
        let activeMethod = null;
        // 해당 객체의 존재유무를 파악하여 생성여부 결정
        function track(target, key) {
            if (activeMethod) {
                let elementsMap = targetMap.get(target);
                if (!elementsMap) {
                    targetMap.set(target, (elementsMap = new Map()));
                }

                let element = elementsMap.get(key);
                if (!element) {
                    elementsMap.set(key, (element = new Set()));
                }

                element.add(activeMethod);
            }
        }
        // 해당 객체를 실행(?)
        function trigger(target, key) {
            const elementsMap = targetMap.get(target);
            if (!elementsMap) {
                return;
            }
            let element = elementsMap.get(key);
            if (element) {
                element.forEach((acMethod) => {
                    acMethod();
                })
            }
        }

        function selfReactive(target){
            const handler = {
                get(target, key, receiver){
                    let result = Reflect.get(target, key, receiver);
                    track(target, key)
                    return result;
                },
                set(target, key, value, receiver){
                    let oldValue = target[key];
                    let result = Reflect.set(target, key, value, receiver);
                    if(result && oldValue !== value){
                        trigger(target, key);
                    }
                    return result;
                }
            }
            return new Proxy(target, handler);
        }

        function selfRef(raw) {
            const r = {
                get value() {
                    track(r, 'value')
                    return raw
                },
                set value(newVal) {
                    raw = newVal
                    trigger(r, 'value')
                },
            }
            return r
        }

        function acMethod(method){
            activeMethod = method;
            activeMethod();
            activeMethod = null;
        }
        let product = selfReactive({p : 5, q : 10})
        let total = 0;
        let salePrice = 0;

        acMethod(() => {
            total = product.p * product.q;
        });
        // 아래와 같이 total로 사용시 변경되는 값은 product.p이므로 slaePrice가 정상작동하지 않는다.
        // acMethod(() => {
        //     salePrice = total * 0.8;
        // });
        acMethod(() => {
            salePrice = product.p * product.q * 0.8;
        });

        console.log('할인가 = ' + salePrice);
        console.log('원가 = ' + total);

        function printll(){
            console.log('할인가 = ' + salePrice);
            console.log('원가 = ' + total);
        }
        return {
            product,
            printll
        }
    }
}
</script>

<style scoped>

</style>
// 객체를 key로 사용하기 위한 WeakMap
const targetMap = new WeakMap;
// Method를 동적으로 사용하기 위한 acctiveMethod
let activeEffect = null;
// 해당 객체의 존재유무를 파악하여 생성여부 결정
function track(target, key) {
    if (activeEffect) {
        let depsMap = targetMap.get(target);
        if (!depsMap) {
            targetMap.set(target, (depsMap = new Map()));
        }

        let dep = depsMap.get(key);
        if (!dep) {
            depsMap.set(key, (dep = new Set()));
        }

        dep.add(activeEffect);
    }
}
// 해당 객체를 실행(?)
function trigger(target, key) {
    const depsMap = targetMap.get(target);
    if (!depsMap) {
        return;
    }
    let dep = depsMap.get(key);
    if (dep) {
        dep.forEach((acMethod) => {
            acMethod();
        })
    }
}

function reactive(target){
    const handler = {
        get(target, key, receiver){
            let result = Reflect.get(target, key, receiver);
            track(target, key);
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

function ref(raw) {
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

function conputed(getter){
    let result = ref();
    effect(() => result.value = getter);
    return result;
}

function effect(method){
    activeEffect = method;
    activeEffect();
    activeEffect = null;
}
let product = reactive({p : 5, q : 10})
let total = 0;
let salePrice = 0;

effect(() => {
    total = product.p * product.q;
});
// 아래와 같이 total로 사용시 변경되는 값은 product.p이므로 slaePrice가 정상작동하지 않는다.
// effect(() => {
//     salePrice = total * 0.8;
// });
effect(() => {
    salePrice = product.p * product.q * 0.8;
});

console.log('할인가 = ' + salePrice);
console.log('원가 = ' + total);

function printll(){
    console.log('할인가 = ' + salePrice);
    console.log('원가 = ' + total);
}

const obj = {};

obj[Symbol('a')] = 'a';
obj[Symbol.for('b')] = 'b';
obj['c'] = 'c';
obj.d = 'd';

console.log(obj[Symbol.for('b')]);
for (const propertyKey in obj) {
    console.log(propertyKey);  // logs 'c' and 'd'
}

//Even Loop Test
console.log('script start'); // A

setTimeout(function () { // B
    console.log('setTimeout');
}, 0);

Promise.resolve()
    .then(function () { // C
        console.log('promise1');
    })
    .then(function () { // D
        console.log('promise2');
    });

console.log('script end'); // E
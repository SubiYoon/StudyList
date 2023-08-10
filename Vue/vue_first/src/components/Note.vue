<template>
    <how-to-use-var-in-tag></how-to-use-var-in-tag>
    <how-to-use-define-expose ref="sampleTest"></how-to-use-define-expose>
  <p v-bind:id="red">이름 : {{!ok ? name : "숨김"}}</p>
  <button :disabled="ok" @click.prevent="함수('알림')">{{age}}</button>
  <button @click="addAge">나이먹기</button>
  <button id="counter" @click="increment">{{ count }}</button>
  <Teleport to="body"></Teleport>
</template>

<script>
import {nextTick, ref, computed, inject} from 'vue'
import HowToUseVarInTag from "@/components/HowToUseVarInTag.vue";
import HowToUseDefineExpose from "@/components/HowToUseDefineExpose.vue";
export default {
    name: "Test",
    components: {HowToUseDefineExpose, HowToUseVarInTag},
    data(){
        return {
        }
    },
    setup(){
        //defineOptions로 다중 추출
        console.log(HowToUseVarInTag.nameFoo);
        const name = ref("홍길동");
        const age = ref(20);
        const red = ref('red');
        const ok = ref(false);
        let fullName = name.value + red.value;

        console.log(fullName);
        name.value = '김갑환';
        //변경되지 않는다. computed를 사용하여 변경해야 변경된다.
        console.log(fullName);
        //다음과 같이 정의해주어야 name의 값이 변경될 때 해당 fullName도 변경이 된다.
        fullName = computed(()=>{
            return name.value + red.value;
        })
        console.log(fullName.value);
        name.value = '발차기';
        console.log(fullName.value);

        function 함수(변수){
            alert(age.value);
        }
        const addAge = function(){
            age.value++;
        };

        console.log(age.value);

        //nextTick Test
        const message = ref('Hello');
        const changeMessage = async newMessage => {
            message.value = newMessage;
            // DOM에서 얻은 값은 이전 값
            await nextTick(()=>{
               setTimeout(()=>console.log("테스트"), 5000);
            });
            // nextTick이 업데이트된 후 DOM 값을 가져온다.
            console.log(('Now DOM is updated'));

        }
        console.log(message.value);
        changeMessage('어쩔건데');
        console.log(message.value);

        const count = ref(0)

        function increment() {
            count.value++

            // DOM not yet updated
            console.log(document.getElementById('counter').textContent) // 0

            // DOM is now updated
            nextTick(()=>{
                console.log(document.getElementById('counter').textContent) // 1
            })
        }

        //어떤 부모의 것이건 자식이 다 받아 올 수 있다.
        console.log(inject('App'))


        class Factory {
            name1 = 'Kim'
            name2 = 'Kim'
        }
        //팩토리 함수를 사용 할 수도 있다.
        const factory = inject('Factory', () => new Factory(), true)

        console.log(factory)


        return {
            name,
            age,
            red,
            ok,
            함수,
            addAge,
            increment,
            count
        }
    }
}
</script>

<style scoped>
  #red {
      color: red;
  }
</style>
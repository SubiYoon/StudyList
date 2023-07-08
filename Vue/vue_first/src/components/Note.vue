<template>
  <p v-bind:id="red">이름 : {{!ok ? name : "숨김"}}</p>
  <button :disabled="ok" @click.prevent="함수('알림')">{{age}}</button>
  <button @click="addAge">나이먹기</button>
</template>

<script>
import {ref, computed} from 'vue'
export default {
    name: "Test",
    setup(){
        const name = ref("홍길동");
        const age = ref(20);
        const red = ref('red');
        const ok = ref(false);
        function 함수(변수){
            alert(age.value);
        }
        const addAge = function(){
            age.value++;
            debugger;
        };

        console.log(age.value);

        return {
            name,
            age,
            red,
            ok,
            함수,
            addAge
        }
    }
}
</script>

<style scoped>
  #red {
      color: red;
  }
</style>
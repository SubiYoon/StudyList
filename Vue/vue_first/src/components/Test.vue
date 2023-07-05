<template>
  <p v-bind:id="red">이름 : {{!ok ? name : "숨김"}}</p>
  <button :disabled="ok" @click.prevent="함수('알림')">{{age}}</button>
</template>

<script>
import {ref} from 'vue'
export default {
    name: "Test",
    setup(){
        const name = ref("홍길동");
        const age = ref(20);
        const red = ref('red');
        const ok = ref(false);
        function 함수(변수){
            alert(변수);
        }
        return {
            name,
            age,
            red,
            ok,
            함수
        }
    }
}
</script>

<style scoped>
  #red {
      color: red;
  }
</style>
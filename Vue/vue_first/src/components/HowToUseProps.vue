<template>
  <div v-for="test, i in userName" :key="i">
      <h1> {{ test.naming }} </h1>
  </div>
  <span>{{ msg }}</span>
</template>

<script>
export default {
    name: "Props",
    data(){
        return {
            msg: "안녕하세요."
        }
    },
    props: {
        userName : {type: Array, default:{}}
    },
    created() {
        console.log(this.$data.msg)
    },
}
</script>

<style scoped>

</style>
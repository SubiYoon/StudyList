<template>
  <div v-for="test, i in userName" :key="i">
      <h1> {{ test.naming }} </h1>
  </div>
</template>

<script>
export default {
    name: "Props",
    props: {
        userName : Array
    },
}
</script>

<style scoped>

</style>
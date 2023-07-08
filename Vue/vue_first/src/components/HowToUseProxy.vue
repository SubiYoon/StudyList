<template>

</template>

<script>
export default {
    name: "ObjectTest",
    setup(){
        const product = {q : 2, p : 5};
        let proxiedProduct = new Proxy(product, {
            get(target, key, receiver){
                console.log('Get was called with key = ' + key);
                return Reflect.get(target, key, receiver);
            }
        })
        console.log(proxiedProduct.q);
        console.log(proxiedProduct["p"]);
    }
}
</script>

<style scoped>

</style>
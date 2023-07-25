<template>
    <ul>
        <li v-for="(item, index) in items" :key="index" :is="getComponent(item.type)">
        {{ item.name }}, {{  }}
        </li>
    </ul>
</template>

<script>
export default {
    data() {
        return {
            items: [
                { type: 'apple', name: '사과' },
                { type: 'banana', name: '바나나' },
                { type: 'orange', name: '오렌지' },
            ],
        }
    },
    methods: {
        getComponent(type) {
            switch (type) {
                case 'apple':
                    return 'AppleItem'
                case 'banana':
                    return 'BananaItem'
                case 'orange':
                    return 'OrangeItem'
                default:
                    return 'DefaultItem'
            }
        },
    },
}
</script>

<style scoped>

</style>
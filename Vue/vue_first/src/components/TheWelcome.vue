<script>
import WelcomeItem from './WelcomeItem.vue'
import DocumentationIcon from './icons/IconDocumentation.vue'
import ToolingIcon from './icons/IconTooling.vue'
import EcosystemIcon from './icons/IconEcosystem.vue'
import CommunityIcon from './icons/IconCommunity.vue'
import SupportIcon from './icons/IconSupport.vue'
import Props from "@/components/HowToUseProps.vue";
import {h, ref, reactive} from 'vue';
import HowToUseProxy from "@/components/HowToUseProxy.vue";
import HowToUseLoopIs from "@/components/HowToUseLoopIs.vue";
import HowToUseRenderFunctions from "@/components/HowToUseRenderFunctions.vue"
import HowToUseDefineExpose from "@/components/HowToUseDefineExpose.vue";
import Note from "@/components/Note.vue";

export default {
    components: {
        HowToUseRenderFunctions,
        HowToUseProxy,
        WelcomeItem,
        DocumentationIcon,
        ToolingIcon,
        EcosystemIcon,
        CommunityIcon,
        SupportIcon,
        Props,
        HowToUseLoopIs,
        HowToUseDefineExpose,
        Note
    },
    methods: {
        //handleClick() {
            //this.$refs.childRef.hello();
        //}
    },
    setup() {
        const list = ref([
            {id: '0', naming: '김씨'},
            {id: '0', naming: '박씨'},
            {id: '0', naming: '이씨'},
            {id: '0', naming: '하씨'},
        ])
        const childRef = ref(null)

        h('div', {id: 'foo'}, 'Reset')
        const handleClick = () => {
            childRef.value.hello()
        }

        return {
            list,
            a: '//www.naver.com',
            h,
            childRef,
            handleClick,
        }
    }
}

</script>

<template>
    <WelcomeItem>
        <!--<Props :user-name="list"></Props>-->
        <how-to-use-proxy/>
        <how-to-use-loop-is/>
    </WelcomeItem>
    <Props :user-name="list"></Props>
    <a :href.prop="a">왓더</a>
    <a :href.attr="'//www.daum.net'" :href="a">나나</a>
    <a :href="a" :href.prop="'//www.daum.net'">나나</a>
    <how-to-use-render-functions/>
    <div>
        <how-to-use-define-expose ref="childRef" />
        <button @click="handleClick">Say Hello</button>
    </div>
    <Note/>
</template>

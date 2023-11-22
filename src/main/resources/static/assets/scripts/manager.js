const {createApp} = Vue;

const App = createApp({

    data(){
        return{
            productsTable : false,
            imagesPanel : true,
            


        }
    },
    created(){

    },
    methods:{
showProductsTable(){
    this.productsTable =!this.productsTable
},
showImagesPanel(){
    this.productsTable =true;
    this.imagesPanel=!this.imagesPanel
},


    }
});
App.mount("#app")
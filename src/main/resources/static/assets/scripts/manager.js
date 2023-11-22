const {createApp} = Vue;

const App = createApp({

    data(){
        return{
            productsTable : false,
            imagesPanel : false,
            messagesPanel :false ,
            


        }
    },
    created(){

    },
    methods:{
showProductsTable(){
    this.productsTable =!this.productsTable
    this.imagesPanel = false
    this.messagesPanel = false
},
showImagesPanel(){
    this.productsTable =true;
    this.imagesPanel=!this.imagesPanel
    this.messagesPanel = false
},
showMessagesPanel(){
    this.productsTable = true;
    this.imagesPanel = false;
    this.messagesPanel=!this.messagesPanel;
}


    }
});
App.mount("#app")
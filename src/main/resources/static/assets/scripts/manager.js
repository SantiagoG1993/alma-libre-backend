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
},
showAddProduct(){
    const item = document.querySelector(".add-producto_modal")
    item.classList.add("show--products--modal")
},
closeAddProduct(){
    const item = document.querySelector(".add-producto_modal")
    item.classList.remove("show--products--modal")
},


    }
});
App.mount("#app")
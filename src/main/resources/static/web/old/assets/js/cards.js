const app = Vue.createApp({
    data() {
        return {
            cards: [],
        }
    },

    created() {
        this.get_cards()
    },

    methods: {
        get_cards() {
            axios.get("/api/clients/1")
                .then(response => {
                    let data = response.data.card_list
                    this.cards = data
                })
        },

    },

    computed: {

    }
}).mount('#app')
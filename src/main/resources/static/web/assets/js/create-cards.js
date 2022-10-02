
const app = Vue.createApp({
    data() {
        return {
            client: {},
            cards_info: {},
            color_input: "",
            type_input: "",
        }
    },

    created() {
        this.get_client()
        this.get_cards_info()

    },

    methods: {
        logout() {
            axios.post('/api/logout',
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => location.href = '/')
        },
        get_client() {
            axios.get("http://localhost:8080/api/clients/current")
                .then(response => {
                    let data = response.data
                    this.client = data
                })
        },
        get_cards_info() {
            axios.get("http://localhost:8080/api/cards/cardsinfo")
                .then(response => {
                    let data = response.data
                    this.cards_info = data
                    this.color_input = this.cards_info.cardColors[0]
                    this.type_input = this.cards_info.cardTypes[0]
                })
        },
        add_card() {
            axios.post('/api/clients/current/cards', "cardType=" + this.type_input
                + "&cardColor=" + this.color_input,
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    location.href = 'cards.html'
                })
                .catch(error => alert(error.response.data))
        }

    },

    computed: {

    }
}).mount('#page-top')
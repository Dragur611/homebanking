
const app = Vue.createApp({
    data() {
        return {
            client: {},
            cards: {},
            card: {},
        }
    },

    created() {
        this.get_cards()
    },

    methods: {
        logout() {
            axios.post('/api/logout',
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => location.href = '/')
        },
        get_cards() {
            axios.get("http://localhost:8080/api/clients/current")
                .then(response => {
                    let data = response.data
                    this.cards = data.cards
                    this.client = data
                    console.log(this.cards)
                })
        },
        show_card(id) {
            let card = this.client.cards.find(card => card.id == id)
            this.card = card
            $('#modalCard').modal('show')
        },
        create_card() {
            location.href = 'create-cards.html'
        },
        delete_card(id) {
            console.log(id)
            axios.delete('/api/clients/current/cards', { data: 'cardId=' + id, headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    alert("Card deleted")
                    location.reload()
                })
                .catch(error => alert(error.response.data))

        }
    },

    computed: {

    }
}).mount('#page-top')
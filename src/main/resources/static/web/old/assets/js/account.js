const app = Vue.createApp({
    data() {
        return {
            transactions: {},
            account_number: "",
        }
    },

    created() {
        this.get_accounts()
    },

    methods: {
        get_accounts() {
            const id = this.get_account_id()
            axios.get("http://localhost:8080/api/accounts/" + id)
                .then(response => {
                    data = response.data.transactions_list
                    this.transactions = data.sort((a, b) => {
                        return a.id - b.id
                    })
                    this.account_number = response.data.account_number
                })
        },
        get_account_id() {
            const params = location.search
            const id = new URLSearchParams(params).get('id')
            return id

        }
    },

    computed: {

    }
}).mount('#app')
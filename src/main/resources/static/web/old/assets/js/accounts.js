const app = Vue.createApp({
    data() {
        return {
            accounts: {},
            loans: {}
        }
    },

    created() {
        this.get_accounts()
    },

    methods: {
        get_accounts() {
            axios.get("http://localhost:8080/api/clients/1")
                .then(response => {
                    let data = response.data
                    let accounts = data.accounts_list
                    this.accounts = accounts.sort((a, b) => {
                        return a.id - b.id
                    })
                    this.loans = data.loans_list
                })
        }
    },

    computed: {

    }
}).mount('#app')
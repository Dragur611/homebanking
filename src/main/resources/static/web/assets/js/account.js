const app = Vue.createApp({
    data() {
        return {
            transactions: {},
            accountNumber: "",
            client: {},
        }
    },

    created() {
        this.get_accounts()
    },

    methods: {
        logout() {
            axios.post('/api/logout',
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => location.href = '/')
        },
        get_accounts() {
            const params = location.search
            const id = new URLSearchParams(params).get('id')
            axios.get("http://localhost:8080/api/clients/current")
                .then(response => {
                    let data = response.data
                    this.client = data
                    let account = data.accounts.find(account => account.id == id)
                    this.transactions = account.transactionsList.sort((a, b) => {
                        return a.id - b.id
                    })
                    this.accountNumber = account.accountNumber
                })
        },
    },

    computed: {

    }
}).mount('#page-top')

const app = Vue.createApp({
    data() {
        return {
            client: {},
            source_acount: "",
            destination_account: "",
            type_input: "internal",
            amount: 0.0,
            description: "",
        }
    },

    created() {
        this.get_client()

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
                    console.log(this.client.accounts)
                })
        },
        add_transaction() {
            console.log("Source: " + this.source_acount)
            console.log("Destination: " + this.destination_account)
            axios.post('/api/transactions', "sourceAccount=" + this.source_acount
                + "&destinationAccount=" + this.destination_account
                + "&amount=" + this.amount
                + "&description=" + this.description,
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    alert("Transfer sent")
                })
                .catch(error => alert(error.response.data))
        }

    },

    computed: {

    }
}).mount('#page-top')
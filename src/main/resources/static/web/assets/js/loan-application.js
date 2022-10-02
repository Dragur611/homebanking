
const app = Vue.createApp({
    data() {
        return {
            client: {},
            loans: {},
            loan: 0,
            destination_account: "",
            amount: 0.0,
            payments: [],
            payments_input: 0,
        }
    },

    created() {
        this.get_client()
        this.get_loan_info()
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
        get_loan_info() {
            axios.get("http://localhost:8080/api/loans")
                .then(response => {
                    this.loans = response.data
                })
        },
        get_payments() {
            this.payments = this.loans.find(loan => loan.id == this.loan).payment
            this.payments.sort((a, b) => a - b)
        },
        loan_apply() {
            const request_body = {
                "idLoan": this.loan,
                "amount": this.amount,
                "payments": this.payments_input,
                "destinationAccount": this.destination_account
            }
            axios.post('/api/loans', request_body,
                { headers: { 'content-type': 'application/json' } })
                .then(response => location.href = '/web/accounts.html')
                .catch(error => alert(error.response.data))
        }
    },

    computed: {

    }
}).mount('#page-top')
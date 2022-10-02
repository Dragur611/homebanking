const app = Vue.createApp({
    data() {
        return {
            client: {},
            accounts: {},
            loans: {},
        }
    },

    created() {
        this.get_info()
        Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif'
        Chart.defaults.global.defaultFontColor = '#858796'

    },

    methods: {
        logout() {
            axios.post('/api/logout',
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => location.href = '/')
        },
        get_info() {
            axios.get("http://localhost:8080/api/clients/current")
                .then(response => {
                    let data = response.data
                    let accounts = data.accounts
                    this.client = data
                    this.accounts = accounts.sort((a, b) => {
                        return a.id - b.id
                    })
                    this.loans = data.loans
                    this.draw_summary()
                })
        },
        go_to_account(id) {
            location.href = 'account.html?id=' + id
        },
        create_account() {
            axios.post('/api/clients/current/accounts',
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => location.reload())
                .catch(error => console.log(error.response))
        },
        draw_summary() {
            let label_data = []
            let data_chart = []
            this.accounts.forEach(account => {
                label_data.push(account.accountNumber)
                data_chart.push(account.balance)
            });
            let ctx = document.getElementById("accountsChart")
            let myPieChart = new Chart(ctx, {
                type: 'doughnut',
                data: {
                    labels: label_data,
                    datasets: [{
                        data: data_chart,
                        backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                        hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                        hoverBorderColor: "rgba(234, 236, 244, 1)",
                    }],
                },
                options: {
                    maintainAspectRatio: false,
                    tooltips: {
                        backgroundColor: "rgb(255,255,255)",
                        bodyFontColor: "#858796",
                        borderColor: '#dddfeb',
                        borderWidth: 1,
                        xPadding: 15,
                        yPadding: 15,
                        displayColors: false,
                        caretPadding: 10,
                    },
                    legend: {
                        display: true
                    },
                    cutoutPercentage: 50,
                },
            });
        }
    },

    computed: {

    }
}).mount('#page-top')
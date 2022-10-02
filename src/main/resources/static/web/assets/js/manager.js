const app = Vue.createApp({
    data() {
        return {
            clients: {},
            name_input: "",
            lastname_input: "",
            email_input: "",
        }
    },
    created() {

        this.get_clients()

    },
    methods: {

        get_clients() {

            axios.get("http://localhost:8080/api/clients")
                .then(response => {
                    this.clients = response.data
                    console.log(response.data)
                })
        },

        add_client() {

            axios.post("http://localhost:8080/api/clients",
                "first_name=" + this.name_input
                + "&last_name=" + this.lastname_input
                + "&email=" + this.email_input)
                .then(() => this.get_clients())

        },

        update_client(id) {

            axios.patch("http://localhost:8080/api/clients",
                "id=" + id
                + "&first_name=" + this.name_input
                + "&last_name=" + this.lastname_input
                + "&email=" + this.email_input)
                .then(() => this.get_clients())

        },

        delete_client(id) {

            axios.delete("http://localhost:8080/api/clients/" + id).then().catch(error => {
                console.log(error)
            })

            /* setTimeout(() => {
                this.get_clients()
            }, 100) */
        }

    },

    computed: {

    }
}).mount('#app')
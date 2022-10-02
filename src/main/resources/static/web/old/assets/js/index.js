$(document).ready(function () {
    $(".veen .rgstr-btn button").click(function () {
        $('.veen .wrapper').addClass('move');
        $('.body').css('background', '#e0b722');
        $(".veen .login-btn button").removeClass('active');
        $(this).addClass('active');

    });
    $(".veen .login-btn button").click(function () {
        $('.veen .wrapper').removeClass('move');
        $('.body').css('background', '#ff4931');
        $(".veen .rgstr-btn button").removeClass('active');
        $(this).addClass('active');
    });
});

const app = Vue.createApp({
    data() {
        return {
            emailLogin: "",
            passwordLogin: "",
            fisrtName: "",
            lastName: "",
            emailRegistration: "",
            passwordRegistration: "",
        }
    },

    created() {

    },

    methods: {

        login() {
            axios.post('/api/login', "email=" + this.emailLogin
                + "&password=" + this.passwordLogin,
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } }).then(response => console.log('signed in!!!'))
        },
        register() {
            axios.post('/api/clients', "firstName=" + this.fisrtName
                + "&lastName=" + this.lastName
                + "&email=" + this.emailRegistration
                + "&password=" + this.passwordRegistration,
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => console.log('registered'))
                .catch(error => alert(error.response.data))
        }

    },

    computed: {

    }
}).mount('#app')
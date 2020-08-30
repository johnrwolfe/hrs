//plain javscript ==================================
     //grab the things we need
     const signupForm = document.getElementById('signup-form');
     const userInput  = signupForm.querySelector('input[name=user]');
     const passwordInput  = signupForm.querySelector('input[name=password]');

    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

     const user = userInput.value;
     const password = passwordInput.value;

     console.log({ user });
    }

   

    // vue ==========================================
    var login = new Vue({
      el: '#signup-form',
      data: {
    	user: '',
    	password: '',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ user: this.user });
        },
      submitProduct: function () {
    	  axios.post('', {
    		    user: this.user,
    		    password: this.password
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
      }
    });
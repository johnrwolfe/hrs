const signupForm = document.getElementById('signup-form');
     const nameInput  = signupForm.querySelector('input[name=name]');
     const maximumDaysInput  = signupForm.querySelector('input[name=maximumDays]');

    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

     const name =  nameInput.value;
     const maximumDays =  maximumDaysInput.value;

     console.log({ name, maximumDays});
    }

   

    // vue ==========================================
    var app = new Vue({
      el: '#signup-form',
      data: {
    	name: '',
    	maximumDays: '',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ name: this.name, maximumDays: this.maximumDays});
        },
      submitProduct: function () {
    	  axios.post('saveLeave', {
    		    name: this.name,
    		    maximumDays: this.maximumDays
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
      }
    });
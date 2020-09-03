const signupForm = document.getElementById('signup-form');
     const amountInput  = signupForm.querySelector('input[name=amount]');

    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

     const amount =  amountInput.value;
     
     console.log({ amount});
    }

   

    // vue ==========================================
    var app = new Vue({
      el: '#signup-form',
      data: {
    	  amount: '',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ amount: this.amount });
        },
      submitProduct: function () {
    	  axios.post('...', {
    		  amount: this.amount
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
      }
    });
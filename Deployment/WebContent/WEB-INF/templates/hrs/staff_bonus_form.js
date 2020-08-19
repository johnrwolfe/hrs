//plain javscript ==================================
     //grab the things we need
     const signupForm = document.getElementById('signup-form');
     const nidInput  = signupForm.querySelector('input[name=nid]');
     const nameInput  = signupForm.querySelector('input[name=name]');
     const startInput  = signupForm.querySelector('input[name=start]');
     const endInput  = signupForm.querySelector('input[name=end]');
    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

     const nid =  nidInput.value;
     const name =  nameInput.value;
     const start =  actionInput.value;
     const end =  endInput.value;

     console.log({ nid, name, start, end});
    }

   

    // vue ==========================================
    var app = new Vue({
      el: '#signup-form',
      data: {
    	nid: '',
    	name: '',
    	start: '',
    	end:'',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ nid: this.nid, name: this.name, start: this.start, end: this.end});
          
        },
      submitProduct: function () {
    	  axios.post('saveEmployeeBonus', {
    		    nid: this.nid,
    		    name: this.name,
    		    start: this.start,
    		    end: this.end
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
      }
    });
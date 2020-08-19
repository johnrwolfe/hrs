	 //plain javscript ==================================
     //grab the things we need
     const signupForm = document.getElementById('signup');
     const fnameInput  = signupForm.querySelector('input[name=fname]');
     const lnameInput = signupForm.querySelector('input[name=lname]');
     const nidInput = signupForm.querySelector('input[name=nid]');

    //listen for the submit event
     signupForm.addEventListener('submit', processSignupForm);
     function processSignupForm(e) {
     e.preventDefault();

     const fname = fnameInput.value;
     const lname = lnameInput.value;
     const nid = nidInput.value;

     console.log({ fname, lname, nid});
     
    }

   

    // vue ==========================================
    var form = new Vue({
      el: '#signup',
      data: {
        fname: '',
        lname: '',
        nid: '',
        errors: {
          name: false,
          email: false
        }
      },
      methods: {
        processForm: function() {
          console.log({ fname: this.fname, lname: this.lname, nid: this.nid});
          
        },
      submitProduct: function () {
    	  axios.post('saveEmployee', {
            	fname: this.fname,
            	lname: this.lname,
            	nid: this.nid
            }).then(response => {
                window.location.href = 'hrs';
            })
        }
       
      }
    });

    

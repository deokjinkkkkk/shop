$(document).ready(function() {
  const $signInBtn = $('#toggleSignIn');
  const $signUpBtn = $('#toggleSignUp');

  const $signUpForm = $('#sign-up-container');
  const $signInForm = $('#sign-in-container');

  // Change form when clicking on button
  const changeForm = ($form1, $form2) => {
    $form1.toggleClass('hide');
    $form2.toggleClass('hide');
  }

  // Show the Sign In form
  $signInBtn.click(() => {
    changeForm($signUpForm, $signInForm);
  });

  // Show the Sign Up form
  $signUpBtn.click(() => {
    changeForm($signUpForm, $signInForm);
  });
});

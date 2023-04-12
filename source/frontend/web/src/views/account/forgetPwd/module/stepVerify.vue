<template>
  <div></div>
</template>
<script>
export default {
  name: 'StepVerify',
  props: {
    authCode: {
      type: String,
      default: ''
    },
    object: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      verifyForm: {
        authCode: this.authCode,
        object: this.object,
        authType: true
      }
    };
  },
  mounted: function() {
    this.$http({
      url: '/forgetPass/verify',
      method: 'post',
      params: this.verifyForm
    }).then(res => {
      if (res.code === 200) {
        this.$router.push('/forgetPwd/stepThree/' + res.object);
      } else {
        this.$router.push('/404');
      }
    }).catch(() => {
      this.$router.push('/404');
    });
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function() {
      history.pushState(null, null, document.URL);
    });
  }
};
</script>

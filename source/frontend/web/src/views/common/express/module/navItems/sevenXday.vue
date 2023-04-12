<template>
  <div>
    <div class="top-box clearfix">
      <ul>
        <li>10月</li>
        <li>24</li>
      </ul>
    </div>
    <div class="bottom-box">
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
      <div class="list-items">
        <div class="left-date">
          <span>04:12</span>
        </div>
        <div class="left-describe">
          <span>
            <i>[我是描述内容标题]</i>
            我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容_我是描述内容
          </span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {

    };
  },
  beforeCreate() {

  },
  created() {

  },
  mounted() {

  },
  methods: {

  }
};
</script>
<style lang="scss" scoped>
  .top-box {
    margin-top: 30px;
    margin-bottom: 40px;
    ul:first-child {
      li {
        float: left;
        padding: 5px 7px;
        width: 50px;
        height: 28px;
        text-align: center;
        box-sizing: border-box;
        border: 1px solid #f22;
        color: #f22;
      }
      li:first-child {
        border-right: none;
      }
      li:last-child {
        background-color: #f22;

        color: #fff;
      }
    }
  }
  .bottom-box {
    .list-items {
      box-sizing: border-box;

      display: flex;
      .left-date{
        width: 50px;
        position: relative;
        border-right: 1px solid rgb(228, 228, 228);
        line-height: 20px;
      }
      .left-describe {
        flex: 1;
        padding-left: 12px;
        padding-bottom: 40px;
        line-height: 20px;
        color: rgb(143, 143, 143);
        span {
          i {
            font-style:normal;
            margin-right: 10px;
            font-weight: bold;
            color: rgb(68, 68, 68);
          }
        }
      }
      &:last-child {
        .left-describe {
          padding-bottom: 0px;
        }
      }
    }
  }

</style>

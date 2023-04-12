const filters = {
  keepNumTwo(value) {
    value = Number(value);
    return value.toFixed(2);
  },
  keepNumFour(value) {
    value = Number(value);
    return value.toFixed(4);
  },
  addPercentage(value) {
    return value + '%';
  }
};
export default filters;

const deleteKeyword = () => {
  document.querySelector('.text-input').value = "";
}

const gotoDetail = (id) => {
  axios.get(`/v1/customers/hotels/${id}`)
  .then(response => {
    window.location.href = `/v1/customers/hotels/${id}`;
  }).catch(error => {
    console.error();
  });
}

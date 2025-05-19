function virarPagina(event, destino) {
  event.preventDefault();
  const paragrafos = document.querySelectorAll('body p');

  paragrafos.forEach((p) => {
    p.classList.add('paragrafo-some');
  });

  setTimeout(() => {
    window.location.href = destino;
  }, 600);
}
document.querySelectorAll(".controle").forEach((controle) => {
  const menos = controle.querySelector(".menos");
  const mais = controle.querySelector(".mais");
  const quantidade = controle.querySelector(".quantidade");

  menos.addEventListener("click", () => {
    let valor = parseInt(quantidade.textContent);
    if (valor > 0) {
      quantidade.textContent = valor - 1;
    }
  });

  mais.addEventListener("click", () => {
    let valor = parseInt(quantidade.textContent);
    quantidade.textContent = valor + 1;
  });
});

const receitas = {
  bolo: ["ovos", "leite", "açúcar", "farinha"],
  pão: ["ovos", "leite", "farinha"],
  chocolate: ["manteiga", "leite", "açúcar", "chocolate"],
  cachorro_quente: ["ovos", "leite", "farinha", "salsicha"],
  biscoito: ["chocolate", "farinha", "manteiga", "açúcar"],
  pizza: ["sal", "farinha", "leite"],
};

let receitasFeitas = JSON.parse(localStorage.getItem("receitasFeitas")) || [];

document.querySelector(".botao-criar-receita").addEventListener("click", () => {
  const ingredientesSelecionados = [];

  document.querySelectorAll(".controle").forEach((controle) => {
    const nome = controle.getAttribute("data-nome");
    const quantidade = parseInt(
      controle.querySelector(".quantidade").textContent
    );
    if (quantidade > 0) {
      ingredientesSelecionados.push(nome);
    }
  });

  if (ingredientesSelecionados.length === 0) {
    window.location.href = "falha.html";
    return;
  }

  let receitaEncontrada = null;

  for (const [nome, ingredientes] of Object.entries(receitas)) {
    if (
      ingredientes.length === ingredientesSelecionados.length &&
      ingredientes.every((ing) => ingredientesSelecionados.includes(ing))
    ) {
      receitaEncontrada = nome;
      break;
    }
  }

  if (receitaEncontrada) {
    const primeiraVez = !receitasFeitas.includes(receitaEncontrada);
    
    if (primeiraVez) {
      receitasFeitas.push(receitaEncontrada);
      localStorage.setItem("receitasFeitas", JSON.stringify(receitasFeitas));
      
      console.log(`Receita ${receitaEncontrada} desbloqueada pela primeira vez!`);
    }
    
    const todasReceitas = Object.keys(receitas);
    const todasFeitas = todasReceitas.every((nome) =>
      receitasFeitas.includes(nome)
    );

    if (todasFeitas) {
      window.location.href = "final.html";
    } else {
      window.location.href = "sucesso.html";
    }
  } else {
    window.location.href = "falha.html";
  }
});
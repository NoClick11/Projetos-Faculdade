function verificarReceitasDesbloqueadas() {
    const receitasFeitas = JSON.parse(localStorage.getItem("receitasFeitas")) || [];
    
    const mapeamentoReceitas = {
        'bolo': 'img-div-bolo',
        'pão': 'img-div-pao',
        'chocolate': 'img-div-chocolate',
        'cachorro_quente': 'img-div-cachorro',
        'biscoito': 'img-div-cookie',
        'pizza': 'img-div-pizza'
    };
    
    let receitaAtual = null;
    
    for (const [nome, classe] of Object.entries(mapeamentoReceitas)) {
        if (document.querySelector(`.${classe}`)) {
            receitaAtual = nome;
            break;
        }
    }
    
    if (receitaAtual) {
        const receitaFoiFeita = receitasFeitas.includes(receitaAtual);
        const imagemDiv = document.querySelector(`.${mapeamentoReceitas[receitaAtual]}`);
        const textoBloqueado = document.querySelector('.texto-bloqueado');
        
        if (receitaFoiFeita) {
            if (imagemDiv) {
                imagemDiv.style.display = 'block';
                imagemDiv.classList.add('receita-desbloqueada');
            }
            if (textoBloqueado) {
                textoBloqueado.style.display = 'none';
            }
        } else {
            if (imagemDiv) {
                imagemDiv.style.display = 'none';
            }
            if (textoBloqueado) {
                textoBloqueado.style.display = 'block';
                textoBloqueado.classList.add('receita-bloqueada');
            }
        }
    }
    
    const todasReceitas = Object.keys(mapeamentoReceitas);
    const todasFeitas = todasReceitas.every(receita => receitasFeitas.includes(receita));
    
    if (todasFeitas) {
        const trofeus = document.querySelectorAll('.trofeu1, .trofeu2');
        trofeus.forEach(trofeu => {
            trofeu.style.display = 'block';
        });
        
        document.body.classList.add('todas-receitas-completas');
    }
}

document.addEventListener('DOMContentLoaded', function() {
    verificarReceitasDesbloqueadas();
});

function limparProgresso() {
    localStorage.removeItem("receitasFeitas");
    location.reload();
}

function desbloquearTodas() {
    const todasReceitas = ['bolo', 'pão', 'chocolate', 'cachorro_quente', 'biscoito', 'pizza'];
    localStorage.setItem("receitasFeitas", JSON.stringify(todasReceitas));
    location.reload();
}
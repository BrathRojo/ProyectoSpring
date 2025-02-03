function buscarPorCategoria() {
    const categoria = document.getElementById('categoriaSelect').value;
    if (categoria) {
        window.location.href = `/productos?categoria=${categoria}`;
    } else {
        alert("Selecciona una categoría antes de buscar.");
    }
}

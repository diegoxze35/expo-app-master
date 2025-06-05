document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("formEvento");

  if (form) {
    form.addEventListener("submit", function (e) {
      e.preventDefault(); // Evita que se recargue la página

      // Obtener los valores del formulario
      const fecha_inicio = document.getElementById("fecha_inicio").value;
      const fecha_fin = document.getElementById("fecha_fin").value;
      const tipo_evento = document.getElementById("tipo_evento").value;

      // Construir el objeto evento
      const evento = {
        fecha_inicio: fecha_inicio,
        fecha_fin: fecha_fin,
        tipo_evento: tipo_evento
      };

      // Enviar la solicitud al backend
      fetch("http://localhost:8080/api/eventos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(evento)
      })
      .then(response => {
        if (!response.ok) {
          throw new Error("Error al registrar el evento");
        }
        return response.json();
      })
      .then(data => {
        alert("✅ Evento registrado con éxito. ID: " + data.id_evento);
        form.reset(); // Limpiar el formulario
      })
      .catch(error => {
        console.error("Error:", error);
        alert("❌ No se pudo registrar el evento.");
      });
    });
  }
});

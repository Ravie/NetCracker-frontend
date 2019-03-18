function findRoots() {
    let a = parseFloat(document.getElementById("coef_a").value);
    let b = parseFloat(document.getElementById("coef_b").value);
    let c = parseFloat(document.getElementById("coef_c").value);
    if (isNaN(a)) {
        alert("Ошибка при вводе коэффициента A. Измените его и попробуйте снова.");
        return;
    }
    if (a === 0) {
        alert("Коэффициент при первом слагаемом уравнения не может быть равным нулю. Измените его и попробуйте снова.");
        return;
    }
    if (isNaN(b)) {
        alert("Ошибка при вводе коэффициента B. Измените его и попробуйте снова.");
        return;
    }
    if (isNaN(c)) {
        alert("Ошибка при вводе коэффициента C. Измените его и попробуйте снова.");
        return;
    }
    let tbody = document.getElementById("history");
    let row = document.createElement("TR");
    let td1 = document.createElement("TD");
    let td2 = document.createElement("TD");
    let td3 = document.createElement("TD");
    let td4 = document.createElement("TD");

    let equation = a.toString() + "x<sup>2</sup>" + ((b >= 0.0) ? "+" : "") + b.toString() + "x" + ((c >= 0.0) ? "+" : "") + c.toString() + "=0";
    td1.innerHTML = equation;

    let discriminant = Math.pow(b, 2) - (4 * a * c);
    td2.appendChild(document.createTextNode(discriminant));

    let text;
    if (discriminant < 0.0)
        text = "Уравнение " + equation + " не имеет вещественных корней.";
    else if (discriminant === 0.0) {
        let root = (-b / (2 * a)).toString();
        text = "Уравнение " + equation + " имеет один корень: " + root;
        td3.appendChild(document.createTextNode(root));
    } else {
        let root1 = ((-b + Math.sqrt(discriminant)) / (2 * a)).toString();
        let root2 = ((-b - Math.sqrt(discriminant)) / (2 * a)).toString();
        text = "Уравнение " + equation + " имеет два корня: " + root1 + " и " + root2;
        td3.appendChild(document.createTextNode(root1));
        td4.appendChild(document.createTextNode(root2));
    }
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    tbody.appendChild(row);
    document.getElementById("result").innerHTML = text;
}

window.onload = function () {
    document.getElementById("find_roots").addEventListener("click", findRoots);

    let table = document.getElementById('table');
    table.onclick = function (e) {
        let target = e.target;
        while (target.nodeName !== 'TD') {
            target = target.parentNode;
        }
        if (target.nodeName !== 'TD') {
            return;
        }
        let parent = target.parentNode;
        parent.parentNode.removeChild(parent);
    }
};

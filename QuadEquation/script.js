function findRoots() {
    let a = parseFloat(document.getElementById("coef_a").value);
    let b = parseFloat(document.getElementById("coef_b").value);
    let c = parseFloat(document.getElementById("coef_c").value);
    if (isNaN(a)) {
        swal({
            title: "Ошибка ввода!",
            text: "Ошибка при вводе коэффициента A",
            icon: "warning",
        });
        return;
    }
    if (a === 0) {
        swal({
            title: "Неверное значение!",
            text: "Коэффициент при первом слагаемом уравнения не может быть равным нулю",
            icon: "warning",
        });
        return;
    }
    if (isNaN(b)) {
        swal({
            title: "Ошибка ввода!",
            text: "Ошибка при вводе коэффициента B",
            icon: "warning",
        });
        return;
    }
    if (isNaN(c)) {
        swal({
            title: "Ошибка ввода!",
            text: "Ошибка при вводе коэффициента C",
            icon: "warning",
        });
        return;
    }
    let tbody = document.getElementById("history");
    let row = document.createElement("TR");
    let tdArr = [];
    for (let i = 0; i < 4; i++) {
        tdArr[i] = document.createElement("TD");
    }

    let equation = a.toString() + "x<sup>2</sup>" + ((b >= 0.0) ? "+" : "") + b.toString() + "x" + ((c >= 0.0) ? "+" : "") + c.toString() + "=0";
    tdArr[0].innerHTML = equation;

    let discriminant = Math.pow(b, 2) - (4 * a * c);
    tdArr[1].appendChild(document.createTextNode(discriminant));

    let text;
    if (discriminant < 0.0)
        text = "Уравнение " + equation + " не имеет вещественных корней.";
    else if (discriminant === 0.0) {
        let root = (-b / (2 * a)).toString();
        text = "Уравнение " + equation + " имеет один корень: " + root;
        tdArr[3].appendChild(document.createTextNode(root));
    } else {
        let root1 = ((-b + Math.sqrt(discriminant)) / (2 * a)).toString();
        let root2 = ((-b - Math.sqrt(discriminant)) / (2 * a)).toString();
        text = "Уравнение " + equation + " имеет два корня: " + root1 + " и " + root2;
        tdArr[2].appendChild(document.createTextNode(root1));
        tdArr[3].appendChild(document.createTextNode(root2));
    }
    for (let i = 0; i < 4; i++) {
        row.appendChild(tdArr[i]);
    }
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

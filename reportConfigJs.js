document.querySelector("body > div.app > div > div.vcontainer > div > div.test-wrapper.row.view.test-view > div.test-list > div.test-list-tools > ul.tools.pull-left > li > a > span").textContent = "Use Cases";
document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(5) > div.col-lg-6.col-md-12.category-container > div > div.card-header > p").textContent = "Test Case Results Summary";
var n = document.createElement("div");
n.className = "nav-center", n.textContent = "Convo Automation Report";
var l = document.querySelector(".nav-left"), t = document.querySelector(".nav-right"), o = l.parentNode;
o.insertBefore(n, t);
var p = document.querySelector(".nav-center"), r = p.parentNode.offsetWidth,
    u = document.querySelector(".nav-logo").offsetWidth, c = document.querySelector(".nav-right").offsetWidth,
    h = (u - c) / 2;
p.style.paddingLeft = h + "px", document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(3) > div > div > p").innerHTML = "UseCases Passed", document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(4) > div > div > p").innerHTML = "UseCases Failed";
var element = document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(1) > div > div.card-footer > div:nth-child(1) > small"),
    currentText = element.innerText, newText = currentText.replace("tests", "usecases");
element.innerText = newText, newText = (currentText = (element = document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(1) > div > div.card-footer > div:nth-child(2) > small")).innerText).replace("tests", "usecases"), element.innerText = newText;
var element = document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(1) > div > div.card-header > h6"),
    currentText = element.innerText;
element.innerText = currentText.replace("Test", "Use Cases");
var t = document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(5)"),
    e = document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(5) > div.col-lg-6.col-md-12.category-container"),
    o = document.querySelector("body > div > div > div.vcontainer > div > div.container-fluid.p-4.view.dashboard-view > div:nth-child(5) > div.col-lg-6.col-md-12.sysenv-container");
t.removeChild(o), o.classList.remove("col-lg-6"), o.classList.add("col-lg-12"), t.insertBefore(o, e);

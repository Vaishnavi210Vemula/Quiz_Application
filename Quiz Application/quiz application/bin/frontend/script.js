function submitQuestionForm(event) {
    event.preventDefault();
    var form = document.getElementById('addQuestionForm');
    var formData = new FormData(form);
    formData.append("action", "addQuestion");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/quiz", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.status === "success") {
                alert("Question added successfully!");
            } else {
                alert("Error: " + response.message);
            }
        }
    };
    xhr.send(new URLSearchParams(formData));
}

function submitQuizForm(event) {
    event.preventDefault();
    var form = document.getElementById('quizForm');
    var formData = new FormData(form);
    formData.append("action", "takeQuiz");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/quiz", true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.status === "success") {
                alert("Quiz completed!");
            } else {
                alert("Error: " + response.message);
            }
        }
    };
    xhr.send(new URLSearchParams(formData));
}

function startQuiz() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/quiz", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.status === "success") {
                var quizForm = document.getElementById('quizForm');
                quizForm.innerHTML = ""; // Clear previous content
                response.questions.forEach(function(question, index) {
                    var div = document.createElement('div');
                    div.innerHTML = `
                        <label>${question.text}</label><br>
                        ${question.options.map((option, i) => `<input type="radio" name="question${index}" value="${option}"> ${option}<br>`).join('')}
                    `;
                    quizForm.appendChild(div);
                });
                var submitButton = document.createElement('input');
                submitButton.type = 'submit';
                submitButton.value = 'Submit Quiz';
                quizForm.appendChild(submitButton);
            } else {
                alert("Error: " + response.message);
            }
        }
    };
    xhr.send("action=startQuiz");
}

window.addEventListener('load',()=>{
    const form = document.querySelector("#new-task-form");
    const input = document.querySelector("#new-task");
    const list_item = document.querySelector("#tasks");

    form.addEventListener('submit', (e) =>{
        e.preventDefault();

        const task = input.value;

        if(!task){
            alert("Please fill in the task");
            return;
        }

        const task_item = document.createElement("div");
        task_item.classList.add("task");

        const task_content_item = document.createElement("div");
        task_content_item.classList.add("content");

        task_item.appendChild(task_content_item);

        const task_input_item = document.createElement("input");
        task_input_item.classList.add("text");
        task_input_item.type = "text";
        task_input_item.value = task;
        task_input_item.setAttribute("readonly","readonly");

        task_content_item.appendChild(task_input_item);

        const task_actions = document.createElement("div");
        task_actions.classList.add("actions");

        const task_done = document.createElement("button");
        task_done.classList.add("done");
        task_done.innerHTML = "Done";

        const task_edit = document.createElement("button");
        task_edit.classList.add("edit");
        task_edit.innerHTML = "Edit";

        const task_remove = document.createElement("button");
        task_remove.classList.add("remove");
        task_remove.innerHTML = "Remove";

        task_actions.appendChild(task_done);
        task_actions.appendChild(task_edit);
        task_actions.appendChild(task_remove);

        task_item.appendChild(task_actions);

        list_item.prepend(task_item);
        

        input.value = "";


        task_done.addEventListener('click',()=>{
            task_input_item.style.color = 'green';
            task_actions.removeChild(task_done);
            task_actions.removeChild(task_edit);
        })

        task_edit.addEventListener('click',()=>{
            if(task_edit.innerText.toLocaleLowerCase() == "edit"){
                task_input_item.removeAttribute("readonly");
                task_input_item.focus();
                task_edit.innerText = "Save";
            }else{
                task_input_item.setAttribute("readonly","readonly");
                task_edit.innerText = "Edit";
            }

        })

        task_remove.addEventListener('click',()=>{
            const chck = confirm("Are you sure you want to remove this task?");
            if(chck == true){
            list_item.removeChild(task_item);
            }
        })

    })
})
function deleteTask(listId,taskId ){
    $.ajax({
        async: false,
        type: "DELETE",
        url: "/tasklist/" + listId + "/task/" + taskId
    })
}

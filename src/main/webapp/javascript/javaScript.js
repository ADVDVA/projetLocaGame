

//function for submit form language in header (reload page after select new language).
function submitLanguageForm(idForm){
    let domForm = document.getElementById(idForm);
    if(!domForm){
        console.log('form not found with id ['+idForm+'].');
        return;
    }
    domForm.submit();
}


//function for click an element found by id.
function clickId(id){
    let domElements = document.querySelectorAll('[id*="'+id+'"]');
    if(domElements.length < 1){
        console.log('element HTML not found id ['+id+'].');
        return null;
    }
    if(domElements.length > 1){
        console.log('many elements HTML found id ['+id+'].');
        return null;
    }
    domElements[0].click();
}

//function for click element next.
function clickNext(target){
    console.log(target);
    console.log(target.nextSibling);
    target.nextSibling.click();
}


//function for research in table before manny character.
function researchTable(target){
    let valueInput = target.value;
    if(valueInput.length >= 3){
        //clickId(idClickFunction);
        clickNext(target);
    }
}

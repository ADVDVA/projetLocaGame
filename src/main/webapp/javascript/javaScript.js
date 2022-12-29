
window.addEventListener('load', function(){
    setTimeout(function(){

        let allLanguageOption = document.getElementsByClassName('languageOption');
        for(let i=0; i<allLanguageOption.length; i++)
            allLanguageOption[i].addEventListener('click', function(){
                setTimeout(function(){
                    document.getElementById('headerLanguageButtonContainer').submit();
                }, 200);
            });

    }, 5);
});
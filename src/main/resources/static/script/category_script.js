const categoryTypes = document.getElementsByTagName('input');

let categoriesCount = categoryTypes.length;

const categoriesBlock = document.getElementById('categories-block');

function setCategories(id){
    let categories = document.getElementsByClassName('category');
    if(categories.length > 0){
        for(let i = 0; i < categories.length; i++){
            if(id == 'all'){
                categories[i].style.display = 'block';
                continue;
            }
            if(categories[i].className.indexOf(id) > -1){
                categories[i].style.display = 'block';
            }else{
                categories[i].style.display = 'none';
            }
        }
    }
}

for(let i = 0; i < categoriesCount; i++){
    categoryTypes[i].onclick = function(){
        setCategories(categoryTypes[i].id);
    }
}


function makePages(page, size, total){
    console.log(page, size, total)

    // 시작 페이지 번호
    const startNum = (Math.ceil(page/10) * 10) - 9

    console.log("startNum", startNum)

    // let은 변수를 바꿀 수 있음
    let result = ""

    // 이전 버튼
    if(startNum !== 1){
      result += `<li class="page-item"><a class="page-link" href="${startNum-1}">&laquo;</a></li>`
    }

    // 페이지 번호
    let temp = startNum  // 임시 번호

    while(true){
      if(temp * size > total){
        break
      }
      console.log(temp)

      result += `<li class="page-item"><a class="page-link" href="${temp}">${temp}</a></li>`

      temp++
    }

  // 다음 버튼
    if(total % (size * 10) === 1){
        result += `<li class="page-item"><a class="page-link" href="${startNum+10}">&raquo;</a></li>`
    }

    console.log(result)
    return result
  } // end while
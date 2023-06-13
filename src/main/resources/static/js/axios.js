// await: get 방식으로 동작할 때까지 기다림
// 비동기 함수지만 일반 동기화 된 함수처럼 사용
// await, asynce는 항상 같이 사용
// async 함수의 호출은 promise 반환
const getPosts = async() => {
    const res = await axios.get(path)
    return res.data
}

const getPostsOne = async (id) => {
    const res = await axios.get(`${path}/${id}`)
    return res.data
}

const deletePostOne = async (id) => {
    const res = await axios.delete(`${path}/${id}`)
    return res.data
}

// 등록
const postOne = async (data) => {
    const res = await axios.post(`${path}`,data)
    return res.data
}

// 수정
const putPostOne = async (data) => {
    const res = await axios.put(`${path}/${data.id}`, data)
    return res.data
}

function getDataDefault(){
    getPosts().then(arr => {
        let str = ""
        console.log(arr)
        for(let i = 0; i < arr.length; i++){
            const {id, title, author} = arr[i]
            str += `<li data-id=${id}>${title}</li>`
        }
        //console.log(str)
        postsDiv.innerHTML = str
    })
}
const BASE_API_JAVA = '/api/java';
const BASE_API_GO = '/api/go';
const GET_INFO_JAVA = {
    url: BASE_API_JAVA + '/getInfo',
    deacriptions: '获取服务器信息'
};
const GET_INFO_GO = {
    url: BASE_API_GO + '/getInfo',
    deacriptions: '获取服务器信息'
}


//一定要注册才可以使用
export default {
    GET_INFO_JAVA: GET_INFO_JAVA,
    GET_INFO_GO: GET_INFO_GO
}

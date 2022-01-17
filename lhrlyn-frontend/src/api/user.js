import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

// 获取用户信息和权限信息
export function getInfo(token) {
  return request({
    url: '/lhrlyn/admin/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/lhrlyn/admin/user/logout',
    method: 'post'
  })
}

# 钉钉群里创建机器人
# 设置自定义关键字：报警
curl 'https://oapi.dingtalk.com/robot/send?access_token=4acbf3675e8ec5923bb890d05c315bb4221ba4b9d4062c01b67939cfed02e612' \
 -H 'Content-Type: application/json' \
 -d '{"msgtype": "text","text": {"content":"应用触发 OOM 报警，请尽快处理"}}'
 
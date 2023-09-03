# tubectl usage

## tubectl 命令格式

```bash
$ ./bin/tubectl.sh {topic|message} {query|add|modify|delete|produce|consume} args
```

---
### 生产与消费

#### 生产消息
命令格式
```bash
$ ./bin/tubectl.sh message produce args
```

e.g.
```bash
$ ./bin/tubectl.sh message produce --master-servers 127.0.0.1:8715 --topicName test
```

#### 消费消息
命令格式
```bash
$ ./bin/tubectl.sh message consume args
```

---
### topic 增删改查

#### query查询
命令格式
```bash
$ ./bin/tubectl.sh topic query args
```

e.g. 查找所有topic
```bash
$ ./bin/tubectl.sh topic query 
```


e.g. 查找指定topicName的topic
```bash
$ ./bin/tubectl.sh topic query --topicName test
```

e.g. 查找指定brokerId的topic
```bash
$ ./bin/tubectl.sh topic query --brokerId 1
```

#### add增加
命令格式
```bash
$ ./bin/tubectl.sh topic add args
```

e.g. 增加topic; 以下四个参数为必选参数
```bash
$ ./tubectl.sh topic  add  --topicName test  --confModAuthToken abc  --createUser user0 --brokerId 1 
```

e.g. 增加topic; 设置为不可订阅(默认为可以订阅)
```bash
$ ./tubectl.sh topic  add  --topicName test  --confModAuthToken abc  --createUser user0 --brokerId 1  --acceptSubscribe false
```

#### modify修改

命令格式
```bash
$ ./bin/tubectl.sh topic modify args
```

e.g. 修改topic; 修改为不可发布
```bash
$ ./tubectl.sh topic  add  --topicName test  --confModAuthToken abc  --createUser user0 --brokerId 1  --acceptPublish false
```
#### delete删除

命令格式
```bash
$ ./bin/tubectl.sh topic delete args
```

e.g. 删除指定topic
```bash
$ ./tubectl.sh topic delete  --topicName test  --modifyUser user0  --confModAuthToken abc --brokerId 1 
```
---
### 参数选项

#### tubectl.sh 参数

```
Usage: tubemq {topic|message} {query|add|modify|delete|produce|consume}
       query:      query topic info
       add:        add new topic
       modify:     modify topic info
       delete:     delete topic
       produce:    produce messages
       consume:    consume messages
```

#### tubectl topic 参数
```
usage: tubectl.sh
    --acceptPublish <bool: if topic accepts publish>                                               Determine if topic accepts publish.
    --acceptSubscribe <bool: if topic accepts subscribe>                                           Determine if topic accepts subscribe.
    --broker-portal <String: format is broker_ip:broker_webport>                                   Broker Service URL to which to connect.(default: 127.0.0.1:8081)
    --brokerId <String: broker id>                                                                 The broker to operator on.
    --confModAuthToken <String:the authorized key for configuration update>                        the authorized key for configuration update
    --createUser <String:the creator>                                                              the creator
    --deletePolicy <Int: default policy for deleting, default: "delete, 168">                      default policy for deleting, default: "delete, 168"
    --deleteWhen                                                                                   String: default deleting time of the topic data. The format should like cronjob form 0 0 6, 18 * * ?
 -h,--help                                                                                         Print usage information.
    --method <String: http call method>                                                            Http call method
    --modifyUser <String:the modifier>                                                             the modifier
 -name,--topicName <String:the topic name>
    --numPartitions <Int: default partition number of a default topic on the broker. default: 3>   default partition number of a default topic on the broker. default: 3
    --show-methods                                                                                 Return http's methods.
    --unflushInterval <Int:the maximum interval for unflush, default 1000ms>                       the maximum interval for unflush, default 1000ms
    --unflushThreshold                                                                             Int: maximum message number which allows in memory. It has to be flushed to disk if the number exceed this value. Default 1000
 -v,--version                                                                                      Display TubeMQ version.
```
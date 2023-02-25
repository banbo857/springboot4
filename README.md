发现原来是 ”*“ 导致的 实际上我们吧列名称声明清楚就解决问题了！！！

大家在使用sharding-jdbc 遇上这种场景的时候 切记，将列名声明清楚，否则 ShardingResultSet 无法识别到列名

正确案例

select name,age from users
# SFTP

SFTP를 통한 파일 업로드 예제. 가맹점의 ID를 입력받은 후, 하위 가맹점을 조회해서 파일을 업로드 합니다. 파일 업로드 과정에서 SFTP를 사용합니다. 파일 업로드가 완료되면, 업로드된 파일을 다운로드 할 수 있습니다.

> SFTP를 통한 파일 업로드/다운로드는 로컬 환경에서 진행합니다.

<br/><br/>

Mac SFTP 설정을 하기 전, **`개인정보 보호 및 보안`** 에서 **`전체 디스크 접근 권한`** 을 허용해야 합니다.  

```shell
# SFTP 실행
$ sudo systemsetup -setremotelogin on
```

```shell
# SFTP 실행여부 확인
$ sudo systemsetup -getremotelogin
Remote Login: On

$ sudo launchctl list | grep ssh
-	0	com.openssh.sshd
```

```shell
# 접속 
$ sftp $(whoami)@localhost

The authenticity of host 'localhost (::1)' can't be established.
ED25519 key fingerprint is SHA256:/XXXXXXXXXXXXAAAAQQQQXXXXQQQQVVVVAAAAVVVVFF.
This key is not known by any other names.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added 'localhost' (ED25519) to the list of known hosts.

Connected to localhost.
```

$(document).ready(function(){
    let cropper;
    const image = document.getElementById('editCropperImage');

    // cropper 생성 메소드
    function createCropper() {
        if (cropper) {
            cropper.destroy();
        }

        cropper = new Cropper(image, {
            aspectRatio: 1 / 1,
            crop(event) {
                updatePreview();
            }
        });
    }
    // 미리보기 canvas 추적 메소드
    function updatePreview() {
        const canvasOptions = {
            width: 250,
            height: 250
        };
        const canvas = cropper.getCroppedCanvas(canvasOptions);
        const previewImage = document.getElementById('previewImage');
        previewImage.src = canvas.toDataURL('image/png');
    }
    createCropper();

    // 사진 변경 
    $("#profileImageInput").change(function() {
        var reader = new FileReader();
        
        reader.onload = function(e) {
            $("#editCropperImage").attr("src", e.target.result);
            createCropper();
        }
        
        reader.readAsDataURL(this.files[0]);
    });

    // 사진 우측 회전
    $("#rotateRightBtn").click(function() {
        cropper.rotate(45);
    });

    // 사진 좌측 회전
    $("#rotateLeftBtn").click(function() {
        cropper.rotate(-45);
    });


    // 드래그 모드 변경
    $("#dragModeMoveBtn").click(function() {
        cropper.setDragMode('move');
    });

    $("#dragModeCropBtn").click(function() {
        cropper.setDragMode('crop');
    });

    // 상하반전 및 좌우반전
    let flippedHorizontal = false;
    let flippedVertical = false;
    $("#flipHorizontalBtn").click(function() {
        flippedHorizontal = !flippedHorizontal;
        cropper.scaleX(flippedHorizontal ? -1 : 1);
    });

    $("#flipVerticalBtn").click(function() {
        flippedVertical = !flippedVertical;
        cropper.scaleY(flippedVertical ? -1 : 1);
    });

    // 리셋
    $("#resetBtn").click(function() {
        cropper.reset();
    });
});